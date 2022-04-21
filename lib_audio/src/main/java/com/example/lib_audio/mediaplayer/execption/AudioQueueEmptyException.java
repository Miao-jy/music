package com.example.lib_audio.mediaplayer.execption;

public class AudioQueueEmptyException extends RuntimeException{
    public AudioQueueEmptyException(String error) {
        super(error);
    }
}
