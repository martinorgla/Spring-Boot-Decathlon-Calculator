import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {DecathlonService} from "../service/decathlon.service";
import {Points} from "../models/points.model";

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.scss']
})
export class HistoryComponent implements OnInit, OnDestroy {
  resultsSubscription: Subscription;

  public resultsList: Points[];

  constructor(
    private decathlonService: DecathlonService
  ) { }

  ngOnInit() {
    this.getResults();
  }

  ngOnDestroy() {
    if (this.resultsSubscription) {
      this.resultsSubscription.unsubscribe();
    }
  }

  /*
   * Get results
   */
  getResults() {
    this.resultsSubscription = this.decathlonService.getResults().subscribe(
      (data) => {
        this.resultsList = data;
      });
  }
}
