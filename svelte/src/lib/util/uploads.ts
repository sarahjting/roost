import FileCodeIcon from "$lib/components/icons/FileCodeIcon.svelte";
import FileIcon from "$lib/components/icons/FileIcon.svelte";
import FileImageIcon from "$lib/components/icons/FileImageIcon.svelte";
import FileTextIcon from "$lib/components/icons/FileTextIcon.svelte";
import type { PageablePage } from "$lib/types/common/PageablePage";
import type { Upload } from "$lib/types/Upload";
import type { AxiosResponse } from "axios";
import { api } from "./api";

export enum UploadType {
    IMAGE = "IMAGE",
    FILE = "FILE",
    TEXT = "TEXT",
    HTML = "HTML",
};

export const UploadTypeOptions: {[key: string]: {icon: any, label: string}} = {
    [UploadType.IMAGE]: {
        icon: FileImageIcon,
        label: "Image",
    },
    [UploadType.TEXT]: {
        icon: FileTextIcon,
        label: "Text",
    },
    [UploadType.HTML]: {
        icon: FileCodeIcon,
        label: "Code",
    },
    [UploadType.FILE]: {
        icon: FileIcon,
        label: "File",
    },
};

export enum UploadSort {
    CREATED_AT_ASC = 'createdAt_asc',
    CREATED_AT_DESC = 'createdAt_desc',
    UPDATED_AT_ASC = 'updatedAt_asc',
    UPDATED_AT_DESC = 'updatedAt_desc',
    SIZE = 'fileSize_desc',
};

export enum UploadFilter {
    UPLOADED_AT_FROM,
    UPLOADED_AT_TO,
    MIME_TYPE,
    TYPE,
    FILE_NAME,
};

export const mimeTypeToUploadType = (mimeType: string) => {
    const mimeTypeGroup = mimeType.split('/')[0];
    if (mimeTypeGroup == 'image') {
        return UploadType.IMAGE;
    } else if (mimeTypeGroup == 'text') {
        return UploadType.IMAGE;
    } else {
        return UploadType.HTML;
    }
}

export const indexUploads = (options: {
        page?: number, 
        sort?: UploadSort, 
        filters?: Array<{filter: UploadFilter, value: any}>
    } = {}) => {
    options = {
        ...{page: 1, sort: UploadSort.CREATED_AT_DESC, filters: []}, 
        ...options
    }
    return api.get(`me/uploads?page=${options.page}&sort=${options.sort}&${options.filters?.map((f) => `${f.filter}=${f.value}`).join('&')}`)
        .then((res: AxiosResponse<PageablePage<Upload>>) => {
            return res.data;
        });
};

export const createUploads = (files: Array<File>) => {
    return api.post("me/uploads", {data: {files}})
        .then((res: AxiosResponse<Array<Upload>>): Array<Upload> => {
            return res.data;
        });
};
