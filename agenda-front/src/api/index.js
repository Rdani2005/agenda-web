import axios from "axios";

const url = "http://localhost:8080/api/" // URL of the API

// instance
const instance = axios.create({
    baseURL: url
})

/**
 * execute a post request without auth
 * @param {*} url url to consult 
 * @param {*} data data to add
 * @returns server response
 */
const post = (url, data) => {
    return instance.post(url, data)
}

/**
 * execute a post request with auth
 * @param {*} url url to consult 
 * @param {*} data data to add
 * @returns server response
 */
const postWithToken = async (url, data) => {
    const token = localStorage.getItem("token")

    if (token) {
        return await instance.post(url, data, {
            headers: {
                'Authorization': "Bearer " + token
            }

        })
    }

    return {
        data: {
            dailed: true
        }
    }
}

/**
 * execute a get request without auth
 * @param {*} url url to consult
 * @returns server response
 */
const get = (url) => {
    return instance.get(url)
}


/**
 * execute a get request with auth
 * @param {*} url url to consult
 * @returns server response
 */
const getWithToken = async (url) => {
    const token = localStorage.getItem("token")

    if (token) {
        return await instance.get(url, {
            headers: {
                'Authorization': "Bearer " + token
            }

        })
    }

    return {
        data: {
            dailed: true
        }
    }
}


/**
 * execute a put request without auth
 * @param {*} url url to consult 
 * @param {*} data data to add
 * @returns server response
 */
const put = (url, data) => {
    return instance.get(url, data)
}


/**
 * execute a put request with auth
 * @param {*} url url to consult 
 * @param {*} data data to add
 * @returns server response
 */
const putWithToken = async (url, data) => {
    const token = localStorage.getItem("token")

    if (token) {
        return await instance.put(url, data, {
            headers: {
                'Authorization': "Bearer " + token
            }

        })
    }

    return {
        data: {
            dailed: true
        }
    }
}

/**
 * execute a delete request with auth
 * @param {*} url url to consult
 * @returns server response
 */
const deleteWithToken = async (url) => {
    const token = localStorage.getItem("token")

    if (token) {
        return await instance.delete(url, {
            headers: {
                'Authorization': "Bearer " + token
            }

        })
    }

    return {
        data: {
            dailed: true
        }
    }
}

export default instance

export { post, postWithToken, get, getWithToken, put, putWithToken, deleteWithToken } 