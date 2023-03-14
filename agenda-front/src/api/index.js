import axios from "axios";

const url = "http://localhost:8080/api/"

const instance = axios.create({
    baseURL: url
})

const post = (url, data) => {
    return instance.post(url, data)
}

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

const get = (url) => {
    return instance.get(url)
}

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

const put = (url, data) => {
    return instance.get(url, data)
}

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



export default instance

export { post, postWithToken, get, getWithToken, put, putWithToken } 