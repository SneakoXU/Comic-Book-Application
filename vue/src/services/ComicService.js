import axios from 'axios';

export default{

    searchComicsByName(name){
        return axios.get(`/comics/query/title/${name}`);
    },

    nextComicsSearch(name, page){
        return axios.get(`/comics/query/title/${name}/${page}`)
    },

    searchComicById(id){
        return axios.get(`/comics/query/id/${id}`);
    },

    getThumbnail(id){
        return axios.get(`/comics/thumbnail/${id}`)
    },
}