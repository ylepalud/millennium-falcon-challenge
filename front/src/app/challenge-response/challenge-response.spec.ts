import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChallengeResponse } from './challenge-response';

describe('SubmitChallengeComponent', () => {
  let component: ChallengeResponse;
  let fixture: ComponentFixture<ChallengeResponse>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ChallengeResponse]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChallengeResponse);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
