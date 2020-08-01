import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentWallComponent } from './comment-wall.component';

describe('CommentWallComponent', () => {
  let component: CommentWallComponent;
  let fixture: ComponentFixture<CommentWallComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommentWallComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommentWallComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
