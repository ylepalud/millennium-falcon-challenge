import {Component, Input, OnInit} from '@angular/core';
import {SolveResponse} from "../api/model/solve-response";
import {BackendService} from "../api/backend.service";
import {SolveRequest} from "../api/model/solve-request";
import {Observable} from "rxjs";
import {AsyncPipe, NgFor, NgIf} from "@angular/common";
import {PathComponent} from "../path/path.component";

@Component({
  selector: 'challenge-response',
  standalone: true,
  imports: [
    AsyncPipe,
    NgIf,
    NgFor,
    PathComponent
  ],
  template: `
    <ng-container *ngIf="solveResponse$ | async as solveResponse">
      <p>{{ mapOddToMessage(solveResponse.odd) }}</p>
      <app-path [ways]="solveResponse.path"></app-path>
    </ng-container>
  `,
  styleUrl: './challenge-response.css'
})
export class ChallengeResponse implements OnInit {

  @Input() request!: SolveRequest;
  solveResponse$!: Observable<SolveResponse>;

  constructor(private backendService: BackendService) { }

  ngOnInit(): void {
    this.solveResponse$ = this.backendService.solveChallenge(this.request);
  }

  mapOddToMessage(odd: number): string {
    if (0 < odd && odd < 1) {
      return `Beware, the path isn't not safe. You have ${odd} chance to succeed`;
    }
    if (odd <=0 ) {
      return `You chance to succeed are null... The empire will destroy Endor`;
    }
    return `Nobody can't stop us !! We will succeed before the death star annihilates Endor`;
  }

}
