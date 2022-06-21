const input = require('fs').readFileSync('C:/Users/tomy9/Desktop/Moz1e git/Algorithm/BaekJoon/input.txt').toString().split("\n")

const [N, M, R] = input[0].split(" ").map((i) => Number(i))
const edge = input.slice(1).map((e) => e.split(' ').map(Number))

const graph = new Map()
edge.forEach(([from, to]) => {
    if (graph.has(from)) graph.get(from).push(to)
    else graph.set(from, [to])
    if (graph.has(to)) graph.get(to).push(from)
    else graph.set(to, [from])
})

let count = 1
const visited = new Array(N + 1)
const answer = new Array(N).fill(0)

const dfs = ((R) => {
    visited[R] = true
    answer[R - 1] = count++
    if (!graph.has(R)) return
    const nodes = [...graph.get(R)].sort((a, b) => a - b)
    for (node of nodes) {
        if (!visited[node]) {
            dfs(node)
        }
    }
})

dfs(R)
console.log(answer.join("\n"))