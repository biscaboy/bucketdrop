package com.davidjdickinson.bucketdrop.services;

/**
 * Simple interface that forces all implmenting services
 * to have a single place for the collection name that will be used to
 * map collections to templates.
 */
public interface CloudStorageService {

    String getCollectionName();

}
