export interface SolveRequest {
  countDown: number,
  bountyHunters: Array<{
    planet: string,
    day: number
  }>
}
