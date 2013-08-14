/* This is an example of how to use an X509HostnameVerifier with
 * an SSLSocket.  For example,
 *
 * java VerifierExample ip.appspot.com
 *
 * should succeed, but:
 *
 * java VerifierExample kegel.com
 *
 * should fail with something like:
 *
 * hostname in certificate didn't match: <kegel.com> != <*.pair.com> OR <*.pair.com> OR <pair.com>
 *
 * Note that this is NOT an example of how to do HTTPS correctly, since there
 * are already much better APIs for doing that, which already handle hostname
 * validation.  This is showing how to do hostname validation for a non-HTTPS
 * protocol, but we're still using HTTPS in the example just because there
 * are plenty of servers out there to test on, and you can easily compare the
 * results here with the certificate warnings your browser gives you.
 */

import java.io.*;
import java.net.*;
import java.security.SecureRandom;
import javax.net.ssl.*;
import com.oblong.tls.verifier.*;

public class VerifierExample {
    public static void main (String[] args) throws IOException {
        SSLSocketFactory fact = (SSLSocketFactory)SSLSocketFactory.getDefault();
        SSLSocket        sock = (SSLSocket)fact.createSocket(args[0], 443);

        X509HostnameVerifier vfy = new BrowserCompatHostnameVerifier ();

        vfy . verify (args[0], sock); // throws IOException if not valid

        OutputStream os = sock . getOutputStream ();
        InputStream is = sock . getInputStream ();
        PrintWriter pw = new PrintWriter (os);
        pw . print ("GET / HTTP/1.1\r\n" +
                    "Host: " + args[0] + "\r\n\r\n");
        pw . flush ();
        int c;
        while ((c = is . read ()) >= 0)
            System.out . write (c);
    }
}
