At the moment, this is not so much a library as a strawman, aimed at
demonstrating one possible solution to the question discussed here:

http://stackoverflow.com/questions/18139448/how-should-i-do-hostname-validation-when-using-jsse
http://lists.randombit.net/pipermail/cryptography/2013-August/004910.html

which in turn is motivated by the problem here:

https://crypto.stanford.edu/~dabo/pubs/abstracts/ssl-client-bugs.html

basically, my assertion is that:

- there is no built-in hostname validation for JSSE when using plain
  old SSL sockets (as opposed to JSSE's HTTPS support) which is
  portable across Java 6, Java 7, and Android.  (There is a solution
  for Java 7, and I believe there is a solution for Android, but the
  two are completely different, and there is no solution for Java 6.)

And my proposed solution is to extract the HostnameVerifier
implementations from Apache HttpComponents, and put them into a small,
self-contained library.  (Which is this library.  Or at least will be,
once I add a proper build system and tests.)  This seems to be
relatively straightforward, and portable across the various Java
implementations I want to target.  I'm just curious whether there's a
more accepted, "idiomatic" solution.  Why has no one had a need for
such a library before?  (Or does one already exist?)  Or does everyone
just write their own hostname validation logic from scratch, and I'm
the only person who wants a canned solution?

If I end up using this little library in the project I'm doing for my
employer, I'll eventually give it a proper build system and some
tests.  Of course, if anyone gets to it before me, feel free to submit
a pull request.
