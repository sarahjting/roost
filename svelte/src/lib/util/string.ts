// https://stackoverflow.com/a/18650828
// thank u l2aelba and stackoverflow community wiki
export const formatBytes = function (bytes: number, decimals = 2): string 
{
    if (!bytes)  {
        return '0 B'
    }

    const k = 1024
    const dm = decimals < 0 ? 0 : decimals
    const sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB']

    const i = Math.floor(Math.log(bytes) / Math.log(k))

    return `${parseFloat((bytes / Math.pow(k, i)).toFixed(dm))} ${sizes[i]}`
}