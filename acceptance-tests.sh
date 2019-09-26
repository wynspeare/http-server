#!/bin/bash
set -ex
gem install bundler
wget https://github.com/wynspeare/http_server_spec/archive/master.tar.gz
tar -xzvf master.tar.gz
cd http_server_spec-master && bundle install
bundle exec spinach --tags @simple-get,@simple-head,@simple-post,@simple-redirect

