import { createAsyncThunk, createSlice} from "@reduxjs/toolkit"
import { loginPost } from "../api/memberApi"
import { removeCookie, setCookie } from "../util/cookieUtil"


export interface LoginInfo {
    email: string,
    nickname: string,
    accessToken: string,
    refreshToken: string,
    roleNames: string[],
    status: string
}


const initState: LoginInfo = {
    email: '',
    nickname: '',
    accessToken: '',
    refreshToken: '',
    roleNames: [],
    status: '',
}

export const loginPostAysnc = createAsyncThunk('loginPostAsync', ({email, pw} : {email: string, pw:string}) => {
    console.log("----------------loginPostAysnc-----------------------")
    console.log(email, pw)
    return loginPost(email, pw)
})

const loginSlice = createSlice({
    name: 'loginSlice',
    initialState: initState,
    reducers: {

        save: (state, action) => {
            console.log("save..............")

            return action.payload
        },

        logout: (state, action) => {

            removeCookie("member")
            console.log("logout..........")
        },
    },
    extraReducers: (builder) => {
        builder.addCase(loginPostAysnc.fulfilled, (state, action) => {
            console.log("loginPostAysnc.fulfilled")

            const newState:LoginInfo = action.payload

            newState.status = ('fulfilled')

            setCookie("member", JSON.stringify(newState), 1)

            return newState
        })
        .addCase(loginPostAysnc.pending, (state, action) => {
            console.log("loginPostAysnc.pending")
        
            state.status = 'pending'
        })
        .addCase(loginPostAysnc.rejected, (state, action) => {
            console.log("loginPostAysnc.rejected")
            state.status = 'rejected'
        })
    }
})


export const {save, logout} = loginSlice.actions

export default loginSlice.reducer