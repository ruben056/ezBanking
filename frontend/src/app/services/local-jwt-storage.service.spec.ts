import { TestBed, inject } from '@angular/core/testing';

import { LocalJwtStorageService } from './local-jwt-storage.service';

describe('LocalJwtStorageService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [LocalJwtStorageService]
    });
  });

  it('should ...', inject([LocalJwtStorageService], (service: LocalJwtStorageService) => {
    expect(service).toBeTruthy();
  }));
});
