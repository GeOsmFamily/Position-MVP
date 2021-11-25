import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerticalPagePrimaryComponent } from './vertical-page-primary.component';

describe('VerticalPagePrimaryComponent', () => {
  let component: VerticalPagePrimaryComponent;
  let fixture: ComponentFixture<VerticalPagePrimaryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VerticalPagePrimaryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VerticalPagePrimaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
