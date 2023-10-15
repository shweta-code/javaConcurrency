Concurrency
Certain things one has to cram because these are not used in day to day coding.
1. I have never used join wait and notify.
   I forgot when should I join threads. join after start or before start?
    Thread should be joined after start.

2. The APIs of concurrency are such , it is so easy to fall in pitfalls of API loopholes.
   Like wait has to be waited on monitor which is already acquired.
    It should always be called within infinite loop of metCondition.
   It is done to avoid spurious wakeups.

3. 