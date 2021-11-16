import { TestBed } from '@angular/core/testing';

import { DeviceDetectionService } from './device-detection.service';

describe('DeviceDetectionService', () => {
  let service: DeviceDetectionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DeviceDetectionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
