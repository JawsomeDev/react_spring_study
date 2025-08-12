import { createAsyncThunk, createSlice} from "@reduxjs/toolkit"
import { loginPost } from "../api/memberApi"


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
        logout: (state, action) => {
            console.log("logout..........")
            return {email: ''}
        },
    },
    extraReducers: (builder) => {
        builder.addCase(loginPostAysnc.fulfilled, (state, action) => {
            console.log("loginPostAysnc.fulfilled")

            const newState:LoginInfo = action.payload

            newState.status = ('fulfilled')

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


export const {login, logout} = loginSlice.actions

export default loginSlice.reducer