import type { ReactNode } from "react";
import useCustomLogin from "../../hooks/useCustomLogin";

// 이렇게도 로그인시에만 보이게 처리 가능. 안썼음.
function AuthComponent({children}: {children:ReactNode}) {

    const {loginStatus, moveToLoginReturn} = useCustomLogin()

    if(!loginStatus){
        return moveToLoginReturn()
    }

    return (  
        <>
        {children}
        </>
    );
}

export default AuthComponent;