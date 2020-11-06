@echo off
wmic  process where (commandline LIKE "%%sw_demo%%" and not name="wmic.exe") delete