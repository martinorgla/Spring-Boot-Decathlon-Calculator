import { TestBed } from '@angular/core/testing';

import { DecathlonService } from './decathlon.service';

describe('DecathlonService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DecathlonService = TestBed.get(DecathlonService);
    expect(service).toBeTruthy();
  });
});
