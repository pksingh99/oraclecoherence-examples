package com.nocompany.coherence.incubator.examples.messaging.queue;

import com.oracle.coherence.common.identifiers.Identifier;
import com.oracle.coherence.patterns.messaging.MessagingSession;
import com.oracle.coherence.patterns.messaging.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Hitesh on 5/28/2015.
 */
public class MessaginQueueConsumer implements Runnable {
    private Identifier identifier;
    private MessagingSession session;
    private boolean keepRunning = true;
    private Object consumedObject;
    private Subscriber subscriber;
    Logger logger = LoggerFactory.getLogger(MessaginQueueConsumer.class);

    public void run() {
        while (keepRunning){
            consumedObject  = subscriber.getMessage();
            logger.info("Latency in Nanos {}",(System.nanoTime() - ((Long)consumedObject)));
        }
    }

    public void setKeepRunning(boolean keepRunning) {
        this.keepRunning = keepRunning;
    }

    public MessaginQueueConsumer(Identifier identifier, MessagingSession session) {
        this.identifier = identifier;
        this.session = session;
        this.subscriber = session.subscribe(identifier);
    }

    public Object getConsumedObject() {
        return consumedObject;
    }
}
