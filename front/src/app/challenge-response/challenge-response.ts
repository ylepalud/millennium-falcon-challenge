import {Component, Input, OnChanges, OnInit} from '@angular/core';
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
  templateUrl: './challenge-response.html',
  styleUrl: './challenge-response.css'
})
export class ChallengeResponse implements OnInit, OnChanges {

  @Input() request!: SolveRequest;
  solveResponse$!: Observable<SolveResponse>;

  constructor(private backendService: BackendService) { }

  ngOnInit(): void {
    this.solveResponse$ = this.backendService.solveChallenge(this.request);
  }

  ngOnChanges(): void {
    this.solveResponse$ = this.backendService.solveChallenge(this.request);
  }

  mapOddToMessage(odd: number): string {
    if (0 < odd && odd < 1) {
      return `Beware, the path isn't safe. You have ${odd} chance to succeed`;
    }
    if (odd <=0 ) {
      return `You're chance to succeed are null ... The empire will destroy Endor`;
    }
    return `Nobody can't stop us !! We will succeed before the death star annihilates Endor`;
  }

}
