import axios from "axios"


export const loginPost = async (email: string, pw: string) => {

    const header = {headers: {"Content-Type": "x-www-form-urlencoded"}}

    const form = new FormData()

    form.append('email', email)
    form.append('pw', pw)

    const res = await axios.post(`http://localhost:8080/api/member/login`, form, header)

    await new Promise(resolve => setTimeout(resolve, 2000));

    return res.data
}