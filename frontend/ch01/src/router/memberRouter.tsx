import { lazy, Suspense } from "react"

const Loading = <div>Loading....</div>
const Login = lazy(() => import("../pages/member/loginPage"))

export default function memberRouter(){
    return (
        {
            path: "member",
            children: [
                {
                    path: "login",
                    element: <Suspense fallback = {Loading}><Login/></Suspense>
                }
            ]
        }
    )
}