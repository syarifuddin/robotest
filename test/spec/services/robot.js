'use strict';

describe('Service: robot', function () {

  // load the service's module
  beforeEach(module('robotApp'));

  // instantiate service
  var robot;
  beforeEach(inject(function (_robotService_) {
    robot = _robotService_;
  }));

  it('should do something', function () {
    expect(!!robot).toBe(true);
  });

});
