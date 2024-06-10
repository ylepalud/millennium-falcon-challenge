import {BountyHunter} from "./bounty-hunter";

export interface EmpireFile {
  countdown: number,
  bounty_hunters: Array<BountyHunter>
}
