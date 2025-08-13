import { useDispatch, useSelector } from "react-redux"
import { type AppDispatch, type RootState } from "../store"
import { Navigate, useNavigate } from "react-router"
import { loginPostAysnc, logout, save } from "../slices/loginSlice"
import { useEffect } from "react"
import { getCookie } from "../util/cookieUtil"


const useCustomLogin = () => {
    const dispatch = useDispatch<AppDispatch>()

    // 로그인 상태 객체
    const loginState = useSelector((state: RootState) => state.loginSlice)


    // 로그인 여부
    const loginStatus = loginState.status

    useEffect(() => {
        if(!loginStatus){
            const cookieData = getCookie("member")
            if(cookieData){
                dispatch(save(cookieData))
            }
        }
    },[])

    const navigate = useNavigate()

    const doLogin = async (email: string, pw: string) => {
        dispatch(loginPostAysnc({email, pw}))
    }

    const doLogout = () => {
        dispatch(logout(null))
        navigate("/")
    }

    const moveToLogin = () => {
        navigate("/member/login")
    }

    const moveToLoginReturn = () => {
        return <Navigate replace to="/member/login"/>
    }

    const moveToPath = (path: string) => {
        navigate({pathname: path}, {replace:true})
    }

    return {loginState, loginStatus, doLogin, doLogout, moveToLogin, moveToLoginReturn, moveToPath}
}

export default useCustomLogin