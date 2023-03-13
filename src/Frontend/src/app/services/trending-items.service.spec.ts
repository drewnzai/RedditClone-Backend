import { TestBed } from '@angular/core/testing';

import { TrendingItemsService } from './trending-items.service';

describe('TrendingItemsService', () => {
  let service: TrendingItemsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TrendingItemsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
