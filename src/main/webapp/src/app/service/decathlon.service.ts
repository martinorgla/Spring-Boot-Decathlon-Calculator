import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";
import {environment} from "../../environments/environment";
import {Sport} from "../models/sport.model";
import {Points} from "../models/points.model";

@Injectable({
  providedIn: 'root'
})
export class DecathlonService {

  constructor(private http: HttpClient) { }

  /*
   * Get sports
   */
  getSports() {
    return this.http.get<Sport>(`${environment.apiEndpoint}/sport`).pipe(map(response => response));
  }

  /*
   * Get results
   */
  getResults() {
    return this.http.get<Points[]>(`${environment.apiEndpoint}/results`).pipe(map(response => response));
  }

  /*
   * Get filtered users
   */
  calculatePoints(results) {
    return this.http.post<Points>(`${environment.apiEndpoint}/calculatepoints`, results, {headers:{'Content-Type': 'application/json'}}).pipe(map(response => response));
  }
}
