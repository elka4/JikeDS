#!/usr/bin/env bash
kill -9 $(lsof -ti tcp:8080)