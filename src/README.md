Concurrency
Certain things one has to cram because these are not used in day to day coding.
1. I have never used join wait and notify.
   I forgot when should I join threads. join after start or before start?
    Thread should be joined after start.

2. The APIs of concurrency are such , it is so easy to fall in pitfalls of API loopholes.
   Like wait has to be waited on monitor which is already acquired.
    It should always be called within infinite loop of metCondition.
   It is done to avoid spurious wakeups.

3. When a thread is waiting outside a synchronized method, it is typically in the "Runnable" state or the "Running" state, depending on whether the thread is actively executing code at that moment.

   1. **Runnable State:** If a thread is waiting outside a synchronized method but is ready to execute and is waiting for its turn to be scheduled by the thread scheduler, it is in the "Runnable" state. In this state, the thread is not blocked and can quickly move to the "Running" state once it gets a CPU core for execution.

   2. **Running State:** If a thread is actively executing code and encounters a point in the code where it needs to enter a synchronized method, it will acquire the lock and enter the "Running" state, executing within that synchronized method. Once it exits the synchronized method, it will remain in the "Running" state, continuing to execute the code that follows.

In summary, a thread that is waiting outside a synchronized method is typically in the "Runnable" state if it's waiting for its turn to execute or in the "Running" state if it's actively executing code outside the synchronized method. The "Blocked" state is only entered when a thread is actively waiting for a lock to be released, which would be the case if it's waiting to enter a synchronized block or method that another thread currently holds.

   **What is imp is when two threads try to acquire same lock, one goes into Runnable state and other goes into Blocked state**

4. Lifecycle of a thread. - You can read it offline anywhere.
5. 
