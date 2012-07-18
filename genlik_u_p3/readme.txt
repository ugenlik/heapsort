Java
Heap.java Submission.java
the program takes an instruction file e.g. input.txt with some commands
and then it executes.the first command must be the mazimum size of the
heap. after that normal commands like insert,print,find,change and deleteMIn
can run from the file.we tested with the demo fiel and the outputs are
as expected.

java Submission input.txt res.txt

there is a Heap data structure in Heap.java that helps us to have a
reusable data structure of a heap that stores names with a given
priority when the names are numbers. The complexity is as expected
from the document since we use the hash table "index" to store the
locations of the nodes of a given name. This means very fast accesses
with O(1) along with very fast other operations.
