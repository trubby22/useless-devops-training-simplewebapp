#!/bin/bash

cd ../../
PID_DIR="$(pwd)/simplewebapp.pid"

if test -f "$PID_DIR"; then
  PID_VAL="$(cat "$PID_DIR")"
  if ps -p "$PID_VAL" > /dev/null; then
    kill -9 PID_VAL
  fi
  rm "$PID_DIR"
fi