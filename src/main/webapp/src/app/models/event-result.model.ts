export class EventResult {
  eventId: string;
  result: number;
  points: number;

  constructor(eventId: string, result: number) {
    this.eventId = eventId;
    this.result = result;
  }
}
