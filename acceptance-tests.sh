#!/bin/bash
set -ex
gem install bundler
wget https://github.com/wynspeare/http_server_spec/archive/work-in-progress.tar.gz
tar -xzvf work-in-progress.tar.gz
cd http_server_spec-work-in-progress && bundle install
bundle exec spinach