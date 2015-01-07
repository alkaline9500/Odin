package com.nmanoogian.odin;

/**
 * Created by nicmanoogian on 1/6/15.
 */
public interface ValhallaAsyncDelegate {
    /**
     * Delegate task did finish
     * @param response the string returned from the server
     */
    public void didFinishTask(ValhallaResponse response);
}
