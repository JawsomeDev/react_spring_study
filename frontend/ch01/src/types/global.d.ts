interface PageRequestDto{
    page: number,
    size: number
}

interface PageResponseDto<T>{
    dtoList: T[]
    pageNumList: number[]
    pageRequestDto: PageRequestDto | null
    prev: boolean
    next: boolean
    totalCount: number
    prevPage: number
    nextPage: number
    totalPage: number
    current: number
}

interface PageParam {
    page ?: string | number
    size ?: string | number
}

interface UseCustomMoveReturn {
    moveToList: (pageParam?: PageParam) => void
    moveToModify: (tno:number) => void
    moveToRead: (tno:number) => void
    page: number
    size: number
    refresh: boolean
}

interface ResultModal {
    title: string,
    content: string,
    callbackFn? : () => void
}