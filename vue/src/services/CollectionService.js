import axios from 'axios';

export default{

    getCollections(){
        return axios.get('/collections');
    },

    getPublicCollections(){
        return axios.get('/collections/public');
    },

    getCollectionsById(id){
        return axios.get(`/collections/${id}`);
    },

    addComicToCollection(collectionId, comicId){
        return axios.post(`/collections/${collectionId}/add/${comicId}`);
    },

    removeComicFromCollection(collectionId, comicId){
        return axios.post(`/collections/${collectionId}/add/${comicId}`);
    },

    addCollection(collection){
        return axios.post('/collections/create', collection)
    },

    getCollectionThumbnail(id) {
        return axios.get(`/collection/thumbnail/${id}`);
    },

    viewCollection(id){
        return axios.get(`/collections/${id}`)
    },

    deleteCollection(id){
        return axios.delete(`/collections/${id}`)
    }


}