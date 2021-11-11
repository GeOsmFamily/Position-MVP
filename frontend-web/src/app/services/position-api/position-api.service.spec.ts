import { TestBed } from '@angular/core/testing';

import { PositionApiService } from './position-api.service';

describe('PositionApiService', () => {
  let service: PositionApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PositionApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
