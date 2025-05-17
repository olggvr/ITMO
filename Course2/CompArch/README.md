# OpenMP: what parallel programming model consists of?

### Description
My report discovers inside structure of OpenMP model, compiler work and runtime library. It introducing student to more deep understanding of how it works inside.

**OpenMP** is a high level parallel model related to systems with complex translation and execution mechanism. This report tells:

- How compiler processes OpenMP directives
- Which calls are formed to runtime library
- How threads are managed
- What hidden inside of thread synchronization
- Scheduling loop iterations
- Protection of shared resources

Special attention given technical details of execution: 
- **fork-join model**
- **thread pool**
- **critical section mechanisms**
- **false-sharing**
-  **work with private and shared memory**

**Last, but not least -** OpenMP is comparing with low level tools like POSIX threads, analyzing problems whith performance.


### References
[OpenMP specification](https://www.openmp.org/) \
[YouTube: Tim Mattson — “What is OpenMP really doing?”](https://www.youtube.com/watch?v=pRtTIW9-Nr0&list=WL&ab_channel=ArgonneMeetings%2CWebinars%2CandLectures) \
Using OpenMP — B. Chapman, G. Jost, R. van der Pas
