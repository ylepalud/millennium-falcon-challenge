import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {SolveResponse} from "./model/solve-response";
import {SolveRequest} from "./model/solve-request";


@Injectable({
  providedIn: 'root'
})
export class BackendService {

  private apiUrl = "http://localhost:8080/solve";

  constructor(private httpClient: HttpClient) {
  }

  solveChallenge(request: SolveRequest): Observable<SolveResponse> {
    return this.httpClient.post<SolveResponse>(this.apiUrl, request);
  }
}
