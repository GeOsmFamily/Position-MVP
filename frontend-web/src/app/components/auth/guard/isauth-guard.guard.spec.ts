import { TestBed } from '@angular/core/testing';

import { IsauthGuardGuard } from './isauth-guard.guard';

describe('IsauthGuardGuard', () => {
  let guard: IsauthGuardGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(IsauthGuardGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
