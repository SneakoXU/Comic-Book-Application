import axios from 'axios';

export default{

    searchComicsByName(name){
        return axios.get(`/comics/query/title/${name}`);
    },

    searchComicById(id){
        return axios.get(`/comics/query/id/${id}`);
    },

    getThumbnail(id){
        return axios.get(`/comics/thumbnail/${id}`)
    }


}