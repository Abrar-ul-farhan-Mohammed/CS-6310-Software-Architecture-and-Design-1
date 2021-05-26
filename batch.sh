#!/usr/bin/env bash

mkdir -p ./test_results
exec &> "./test_results/batch_results.txt"

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

for i in {0..10}
do
  echo " ------ Test Case: $i ----"
  tc="00$i"
  "$DIR/test.sh" ${tc:(-2):2}
done
wait