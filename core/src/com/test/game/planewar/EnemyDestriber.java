package com.test.game.planewar;

/**
 * 敌人分配器
 * @author lzero-pc
 *
 */
public interface EnemyDestriber {
	/**
	 * 分配敌人
	 */
	void destribeEnemy();
	/**
	 * 删除过期敌人
	 */
	void destroyEnemy();
}
