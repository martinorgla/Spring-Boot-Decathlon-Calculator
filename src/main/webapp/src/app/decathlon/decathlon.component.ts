import {Component, HostListener, OnDestroy, OnInit} from '@angular/core';
import {DecathlonService} from "../service/decathlon.service";
import {Subscription} from "rxjs";
import {Sport} from "../models/sport.model";
import {EventResult} from "../models/event-result.model";
import {Points} from "../models/points.model";

@Component({
  selector: 'app-decathlon',
  templateUrl: './decathlon.component.html',
  styleUrls: ['./decathlon.component.scss']
})
export class DecathlonComponent implements OnInit, OnDestroy {
  sportSubscription: Subscription;
  calculatePointsSubscription: Subscription;

  public points: Points;
  public sports: Sport;
  public formObject = {};
  public resultArray = [];
  public results = [];

  constructor(
    private decathlonService: DecathlonService
  ) { }

  ngOnInit() {
    this.getSports();
  }

  /*
   * Get sports
   */
  getSports() {
    this.sportSubscription = this.decathlonService.getSports().subscribe(
      (data) => {
       this.sports = data;
      });
  }

  onSubmit () {
    this.results.length = 0;

    Object.keys(this.formObject).forEach(result => this.results.push(new EventResult(result, this.formObject[result])));

    this.calculatePointsSubscription = this.decathlonService.calculatePoints(JSON.stringify(this.results)).subscribe(
      (data) => {
        this.points = data;

        console.log(this.points);

        this.points.eventResultDto.forEach(eventResultDto => this.resultArray[eventResultDto.eventId] = eventResultDto.points);
      }
    )
  }

  // Erki Nool results for 8667 points!
  // https://en.wikipedia.org/wiki/1998_European_Athletics_Championships_%E2%80%93_Men%27s_decathlon
  onErkiNool () {
    this.formObject = {
      "M100": 10.58,
      "LONG_JUMP": 780,
      "SHOT_PUT": 14.40,
      "HIGH_JUMP": 197,
      "M400": 46.67,
      "M110_HURDLES": 14.68,
      "DISCUS_THROW": 40.79,
      "POLE_VAULT": 540,
      "JAVELIN_THROW": 70.65,
      "M1500": 278
    };

    this.onSubmit();
  }

  // Reset formObject by pressing ESC on keyboard
  @HostListener('document:keydown.escape', ['$event']) onKeydownHandler(event: KeyboardEvent) {
    this.formObject = {
      "M100": null,
      "LONG_JUMP": null,
      "SHOT_PUT": null,
      "HIGH_JUMP": null,
      "M400": null,
      "M110_HURDLES": null,
      "DISCUS_THROW": null,
      "POLE_VAULT": null,
      "JAVELIN_THROW": null,
      "M1500": null
    };

    if (this.points.points > 0) {
      this.points.points = 0;
    }
  }

  ngOnDestroy() {
    if (this.sportSubscription) {
      this.sportSubscription.unsubscribe();
    }

    if (this.calculatePointsSubscription) {
      this.calculatePointsSubscription.unsubscribe();
    }
  }
}
