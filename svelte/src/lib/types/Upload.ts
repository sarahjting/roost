import type { UploadType } from "$lib/util/uploads";

export type Upload = {
    id: string,
    fileName: string,
    originalFileName: string,
    fileUrl: string,
    fileSize: number,
    imageHeight: number | null,
    imageWidth: number | null,
    mimeType: string,
    storage: Storage,
    type: UploadType,
    createdAt: string,
    updatedAt: string,
};
