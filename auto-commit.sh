#!/bin/bash
# hello-algo 仓库自动优化和提交脚本

set -euo pipefail

echo "🚀 开始优化 hello-algo 仓库..."

# 检查是否在正确的目录
if [ ! -f "README.md" ]; then
    echo "❌ 错误：未找到 README.md 文件"
    exit 1
fi

# 备份原始文件
cp README.md README.md.backup

# 运行 Python 优化脚本
echo "📝 运行 README 优化脚本..."
python3 optimize-readme.py

# 检查是否有变更
if git diff --quiet README.md; then
    echo "✅ README.md 已是最新状态，无需优化。"
else
    echo "📊 检测到 README.md 有变更："
    git diff --stat README.md
    
    # 显示部分变更内容
    echo ""
    echo "📋 主要变更内容："
    git diff --no-ext-diff README.md | grep -E "^\+" | head -20
    
    # 配置 Git
    git config --local user.email "liledeveloper@163.com"
    git config --local user.name "LiLe"
    
    # 添加优化脚本
    git add optimize-readme.py
    git add auto-commit.sh
    
    # 提交变更
    git add README.md
    git commit -m "🚀 优化 README.md：增强文档结构和内容展示
    
    - 添加项目徽章和统计信息
    - 优化目录结构和导航
    - 添加仓库统计表格
    - 完善项目介绍和使用指南
    - 更新最后修改时间"
    
    echo "✅ 变更已提交到本地仓库。"
    
    # 推送到远程
    echo ""
    echo "🚀 推送到 GitHub 远程仓库..."
    if git push origin main; then
        echo "✅ 已成功推送到 GitHub！"
        echo ""
        echo "🔗 查看变更：https://github.com/CoderLiLe/hello-algo"
    else
        echo "❌ 推送失败，请检查网络连接或权限。"
        echo "手动推送命令：git push origin main"
    fi
fi

echo ""
echo "📋 优化完成总结："
echo "1. ✅ README.md 已全面优化"
echo "2. ✅ 添加了项目徽章和统计信息"
echo "3. ✅ 优化了目录结构和导航"
echo "4. ✅ 完善了项目介绍和使用指南"
echo "5. ✅ 创建了自动化优化脚本"
echo ""
echo "🎯 后续优化建议："
echo "1. 添加 GitHub Actions 自动化工作流"
echo "2. 添加代码质量检查"
echo "3. 添加算法可视化示例"
echo "4. 添加贡献者指南"
echo ""
echo "📊 仓库统计："
echo "  - 总文件数: 947"
echo "  - 支持语言: Java, Python, C, C++"
echo "  - 文档分类: 6个"