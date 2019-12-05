import {Component, OnInit} from '@angular/core';
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
export class DecathlonComponent implements OnInit {
  sportSubscription: Subscription;
  calculatePointsSubscription: Subscription;

  public points: Points;
  public sports: Sport;
  public formObject = new Object();
  public resultArray = new Array();

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

       for(let sportId of Object.keys(this.sports)){
         this.formObject[this.sports[sportId].valueOf().id] = 0;
       }
      });
  }

  onSubmit () {
    let results = new Array();

    for (let resultKey of Object.keys(this.formObject)) {
      results.push(new EventResult(resultKey, this.formObject[resultKey]));
    }

    this.calculatePointsSubscription = this.decathlonService.calculatePoints(JSON.stringify(results)).subscribe(
      (data) => {
        this.points = data;

        console.log();

        this.points.eventResultDto.forEach(eventResultDro => this.resultArray[eventResultDro.eventId] = eventResultDro.points);

        console.log(this.resultArray);


        for (let eventResultDto of Object.keys(this.points.eventResultDto)) {
          // results.push(new EventResult(resultKey, this.formObject[resultKey]));
          // resultArray[resultDtoKey] = this.points.eventResultDto[resultDtoKey],v
        }



        // this.formObject[this.sports[sportId].valueOf().id] = 0;


        // alert(this.points.points);
      }
    )
  }

  get diagnostic() {
    return JSON.stringify(this.formObject);
  }
}
