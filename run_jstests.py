#!/usr/bin/env python
# Copyright 2014 The Chromium Authors. All rights reserved.
# Use of this source code is governed by a BSD-style license that can be
# found in the LICENSE file.

"""Runs DomDistillers jstests.

This uses ChromeDriver (https://sites.google.com/a/chromium.org/chromedriver/) to run the jstests.
This requires that the ChromeDriver executable is on the PATH and that Selenium WebDriver is
installed.

In addition, ChromeDriver assumes that Chrome is available at /usr/bin/google-chrome.
"""

import os
import sys
import time

try:
  from selenium import webdriver
except:
  print 'ERROR:'
  print 'Couldn\'t import webdriver. Please run `sudo ./install-build-deps.sh`.'
  sys.exit(1)

start = time.time()

test_runner = "return com.dom_distiller.client.JsTestEntry.run()";
test_html = os.path.abspath(os.path.join(os.path.dirname(__file__), "war", "test.html"))

driver = webdriver.Chrome()
driver.get("file://" + test_html)
result = driver.execute_script(test_runner)
driver.quit()
end = time.time()
print result['log'].encode('utf-8')
print 'Tests run: %d, Failures: %d, Skipped: %d, Time elapsed: %0.3f sec' % (result['numTests'],
    result['failed'], result['skipped'], end - start)
sys.exit(0 if result['success'] else 1)
