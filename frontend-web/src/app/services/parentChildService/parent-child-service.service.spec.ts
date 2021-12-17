import { TestBed } from '@angular/core/testing';

import { ParentChildServiceService } from './parent-child-service.service';

describe('ParentChildServiceService', () => {
  let service: ParentChildServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ParentChildServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
